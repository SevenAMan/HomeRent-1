package org.zc.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zc.homerent.controller.util.Format;
import org.zc.homerent.controller.util.ReturnStatus;
import org.zc.homerent.entity.Bill;
import org.zc.homerent.service.BillService;
import org.zc.homerent.util.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 17:14
 */
@RestController
public class BillController {
    private final BillService service;

    @Autowired
    public BillController(BillService service) {
        this.service = service;
    }

    @GetMapping("/bill")
    public Format getBill(HttpSession session) {
        String email = (String) session.getAttribute("user");
        if (email == null) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.NO_SUCH_USER);
        }
        List<Bill> re = service.getAll(email);
        return new Format().code(ReturnStatus.SUCCESS)
                .addData("bills", re);
    }
}
