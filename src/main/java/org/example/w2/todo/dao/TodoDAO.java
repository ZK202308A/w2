package org.example.w2.todo.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.ConnectionUtil;
import org.example.w2.todo.TodoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum TodoDAO {
    INSTANCE;

    TodoDAO() {

    }


    public Integer insert( TodoVO todoVO) throws Exception{

        // ; 없는지 주의
        String sql = "insert into  tbl_todo (title,writer) values (?,?)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        con.setAutoCommit(false);

        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, todoVO.getTitle());
        ps.setString(2, todoVO.getWriter());

        int count = ps.executeUpdate();

        if(count != 1){
            throw new Exception("Abnormal insertion");
        }

        ps.close();

        ps = con.prepareStatement("SELECT LAST_INSERT_ID()");

        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();
        Integer tno = rs.getInt(1);

        log.info("TNO:........." + tno);

        con.commit();
        con.setAutoCommit(true);

        return tno;
    }


    public List<TodoVO> list(int page)throws Exception{

        log.info("list");

        int skip = (page - 1) * 10;

        String query = """
            select
                *
            from
                tbl_todo
            where
                delflag = false
            and
                tno > 0
            order by
                tno desc
            limit  ? , 10
        """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, skip);

        @Cleanup ResultSet rs = ps.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (rs.next()) {

            TodoVO vo = TodoVO.builder()
                    .tno(rs.getInt("tno"))
                    .title(rs.getString("title"))
                    .writer(rs.getString("writer"))
                    .regDate(rs.getTimestamp("regdate"))
                    .modDate(rs.getTimestamp("moddatee"))
                    .delFlag(rs.getBoolean("delflag"))
                    .build();

            list.add(vo);

        }


        return list;
    }

    public int getTotal() throws Exception {
        log.info("getTotal");

        String query ="select count(tno) from tbl_todo where tno > 0 and delflag = false";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        int total = rs.getInt(1);

        return total;
    }
}
