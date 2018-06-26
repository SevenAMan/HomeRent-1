package org.zc.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zc.homerent.controller.util.Format;
import org.zc.homerent.controller.util.ReturnStatus;
import org.zc.homerent.entity.House;
import org.zc.homerent.entity.Order;
import org.zc.homerent.service.BillService;
import org.zc.homerent.service.HouseService;
import org.zc.homerent.service.OrderService;
import org.zc.homerent.util.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/26 16:08
 */
@RestController
public class OrderController {

    private final OrderService service;
    private final BillService billService;
    private final HouseService houseService;

    @Autowired
    public OrderController(OrderService service, BillService billService, HouseService houseService) {
        this.service = service;
        this.billService = billService;
        this.houseService = houseService;
    }

    @PostMapping("/order")
    public Format create(@RequestParam int id,
                         @RequestParam long begin,
                         @RequestParam long end,
                         HttpSession session) {
        String email = (String) session.getAttribute("user");
        House h = houseService.findById(id);
        if (email == null || h == null) {
            return new Format().code(ReturnStatus.FAILURE)
                    .message(StringUtil.NO_SUCH_USER);
        }

        Order o = new Order();
        o.setBegin(begin);
        o.setEnd(end);
        o.setHouse(id);
        o.setEmail(email);
        o.setPrice(h.getPrice());

        service.add(o);
        billService.recharge(email, h.getPrice() * -1);

        return new Format().code(ReturnStatus.SUCCESS);
    }

    @GetMapping("/order")
    public Format getOrder(HttpSession session) {
        String email = (String) session.getAttribute("user");
        if (email == null) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.NO_SUCH_USER);
        }
        List<Order> result = service.findByEmail(email);
        return new Format().code(ReturnStatus.SUCCESS)
                .addData("orders", result);
    }
}
