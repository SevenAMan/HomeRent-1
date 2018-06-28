package org.zc.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.zc.homerent.controller.util.FileUtil;
import org.zc.homerent.controller.util.Format;
import org.zc.homerent.controller.util.ReturnStatus;
import org.zc.homerent.entity.House;
import org.zc.homerent.service.HouseService;
import org.zc.homerent.util.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author FDws
 * Created on 2018/6/26 15:20
 */
@RestController
public class HouseController {
    private final FileUtil fileUtil;
    private final HouseService service;

    @Autowired
    public HouseController(FileUtil fileUtil, HouseService service) {
        this.fileUtil = fileUtil;
        this.service = service;
    }

    @PostMapping("/house")
    public Format addHouse(@RequestParam int area,
                           @RequestParam int bed,
                           @RequestParam int living,
                           @RequestParam String message,
                           @RequestParam int price,
                           @RequestParam MultipartFile file,
                           HttpSession session) {
        String email = (String) session.getAttribute("user");
        if (email == null) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.NO_SUCH_USER);
        }
        String hn = fileUtil.saveImage(file);
        House house = new House();
        house.setArea(area);
        house.setBed(bed);
        house.setEmail(email);
        house.setLiving(living);
        house.setMessage(message);
        house.setPrice(price);
        house.setImage(hn);
		house.setType(House.ON_SALE);
        service.add(house);
        return new Format().code(ReturnStatus.SUCCESS);
    }

    @GetMapping("/house")
    public Format getHouse(@RequestParam int price1,
                           @RequestParam int price2,
                           @RequestParam int area1,
                           @RequestParam int area2,
                           @RequestParam int bed,
                           @RequestParam int living) {
        Stream<House> result = service.find(House.ALL)
                .stream().filter(house -> house.getPrice() > price1)
                .filter(house -> house.getPrice() < price2)
                .filter(house -> house.getArea() > area1)
                .filter(house -> house.getArea() < area2);
        if (bed > 0) {
            result = result.filter(house -> house.getBed() == bed);
        }
        if (living > 0) {
            result = result.filter(house -> house.getLiving() == living);
        }
        List<House> re = result.collect(Collectors.toList());
        return new Format().code(ReturnStatus.SUCCESS).addData("houses", re);
    }

    @GetMapping("/house/{name}")
    public StreamingResponseBody houseImage(@PathVariable String name) {
        return outputStream -> fileUtil.readImage(name.replace("@","."), outputStream);
    }

    @GetMapping("/user/house")
    public Format getMyHouse(HttpSession session, @RequestParam int type) {
        String email = (String) session.getAttribute("user");
        if (email == null) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.NO_SUCH_USER);
        }
        List<House> re = service.findAll(email, type);
        return new Format().code(ReturnStatus.SUCCESS).addData("houses", re);
    }
}
