package org.example.w2.member;

import lombok.Cleanup;
import org.example.w2.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public enum MemberDAO {
    INSTANCE;

    public Optional<MemberVO> get(String word, String pw) throws Exception {

        String query = """
                select * from tbl_member
                where
                    (uid = ? or email = ? )
                  and
                    upw = ?
                  and
                    delflag = false
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, word);
        ps.setString(2, word);
        ps.setString(3, pw);

        @Cleanup ResultSet rs = ps.executeQuery();

        if( ! rs.next() ){
            return Optional.empty();
        }
        MemberVO member = MemberVO.builder()
                .mno(rs.getInt("mno"))
                .uid(rs.getString("uid"))
                .upw(rs.getString("upw"))
                .email(rs.getString("email"))
                .delFlag(rs.getBoolean("delflag"))
                .build();

        return Optional.of(member);

    }

}
