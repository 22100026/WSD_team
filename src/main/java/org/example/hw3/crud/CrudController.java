package org.example.hw3.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CrudController {
    @Autowired
    CrudDAO crudDAO;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "category", required = false, defaultValue = "") String category, @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", crudDAO.searchCrud(category, "%" + search + "%")); // 검색
        } else {
            model.addAttribute("list", crudDAO.getListCrud()); // 전체 목록
        }
        return "list";
    }


    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write() {
        return "write";
    }

    @RequestMapping(value = "/write_ok", method = RequestMethod.POST)
    public String writeOk(CrudVO vo){
        int i = crudDAO.insertCrud(vo);
        if (i == 0) {
            System.out.println("data add failed");
        } else {
            System.out.println("data add ok!");
        }
        return "redirect:/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable int id, Model model){
        crudDAO.updateView(id);
        CrudVO vo = crudDAO.getCrud(id);
        model.addAttribute("vo", vo);
        return "view";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        CrudVO vo = crudDAO.getCrud(id);
        model.addAttribute("vo", vo);
        return "edit";
    }

    @RequestMapping(value = "/edit_ok", method = RequestMethod.POST)
    public String editOk(CrudVO vo){
        System.out.println(vo.getName());
        System.out.println(vo.getId());
        System.out.println(vo.getCity());
        System.out.println(vo.getBday());
        System.out.println(vo.getFile());
        System.out.println(vo.getDorm());
        System.out.println(vo.getEtc());
        System.out.println(vo.getGender());
        System.out.println(vo.getPhone());
        System.out.println(vo.getStudent());
        int i = crudDAO.updateCrud(vo);
        if (i == 0) {
            System.out.println("data edit failed");
        } else {
            System.out.println("data edit ok!");
        }
        return "redirect:/list";
    }

    @RequestMapping(value = "/delete_ok/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        int i = crudDAO.deleteCrud(id);
        if (i == 0) {
            System.out.println("data delete failed");
        } else {
            System.out.println("data delete ok");
        }
        return "redirect:/list";
    }
}
