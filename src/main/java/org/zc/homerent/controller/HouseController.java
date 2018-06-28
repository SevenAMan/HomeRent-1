package org.zc.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.zc.homerent.controller.util.FileUtil;
import org.zc.homerent.controller.util.Format;
import org.zc.homerent.controller.util.ReturnStatus;
import org.zc.homerent.dao.HouseDao;
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

    private final HouseDao houseDao;

    @Autowired
    public HouseController(FileUtil fileUtil, HouseService service, HouseDao houseDao) {
        this.fileUtil = fileUtil;
        this.service = service;
        this.houseDao = houseDao;
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
    public Format getHouse() {
        List<House> result = houseDao.findAll()
                .stream().filter(house -> house.getType() != House.SELL_OUT).collect(Collectors.toList());
        return new Format().code(ReturnStatus.SUCCESS).addData("houses", result);
    }

    @GetMapping("/house/{name}")
    public StreamingResponseBody houseImage(@PathVariable String name) {
        return outputStream -> fileUtil.readImage(name.replace("@", "."), outputStream);
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

    @GetMapping("/house/my")
    public Format mHouse(HttpSession session) {
        String email = (String) session.getAttribute("user");
        List<House> houses = houseDao.findAll().stream().filter(house -> house.getEmail().equals(email))
                .collect(Collectors.toList());
        return new Format().code(0).addData("houses", houses);
    }
    @DeleteMapping("/house/{id}")
    public Format del(@PathVariable String id){
        houseDao.delete(houseDao.getOne(Integer.parseInt(id)));
        return new Format().code(ReturnStatus.SUCCESS);
    }
}
