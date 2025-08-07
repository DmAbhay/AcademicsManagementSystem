package in.myedbiz.util;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class Util {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall enrollmentNoCall;
    private SimpleJdbcCall uniqueIdCall;

    @PostConstruct
    public void init() {
        enrollmentNoCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("generate_enrollment_no");

        uniqueIdCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("generate_uniquiId");
    }

    public String generateEnrollmentNo(String departmentCode) {
        Map<String, Object> inParams = Map.of("in_departmentCode", departmentCode);
        Map<String, Object> result = enrollmentNoCall.execute(inParams);
        System.out.println("Procedure output: " + result);
        return (String) result.get("out_enrollmentno");
    }

    public String generateUniqueId(String departmentCode) {
        Map<String, Object> inParams = Map.of("in_departmentCode", departmentCode);
        Map<String, Object> result = uniqueIdCall.execute(inParams);
        System.out.println("Procedure output: " + result);
        return (String) result.get("out_uniquiid");
    }
}

