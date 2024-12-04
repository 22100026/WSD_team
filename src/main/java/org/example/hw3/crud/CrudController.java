package org.example.hw3.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class CrudController {
    @Autowired
    CrudDAO crudDAO;



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "category", required = false, defaultValue = "") String category, @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", crudDAO.searchCrud(category, "%" + search + "%")); // 검색
        } else {
            model.addAttribute("list", crudDAO.getCrudList()); // 전체 목록
        }
        return "list";
    }


    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write() {
        return "write";
    }

    @RequestMapping(value = "/write_ok", method = RequestMethod.POST)
    public String writeOk(CrudVO vo, @RequestParam("file") MultipartFile file, Model model) {
        try {
            System.out.println("Received VO: " + vo.toString());
            System.out.println("Received File: " + file.getOriginalFilename());
            // 파일 업로드 처리
            if (!file.isEmpty()) {
                // 저장 경로 설정
                String uploadPath = "/path/to/upload/directory/";
                String fileName = file.getOriginalFilename();
                File dest = new File(uploadPath + fileName);

                // 동일 파일 삭제 후 업로드
                if (dest.exists()) {
                    dest.delete();
                }
                file.transferTo(dest);

                // 업로드된 파일 이름을 VO에 설정
                vo.setFilename(fileName);

                System.out.println("파일 업로드 성공: " + fileName);
            } else {
                vo.setFilename(null); // 파일이 없는 경우 null로 처리
                System.out.println("파일이 선택되지 않았습니다.");
            }

            // 데이터 삽입 처리
            int i = crudDAO.insertCrud(vo);
            if (i == 0) {
                System.out.println("데이터 추가 실패");
            } else {
                System.out.println("데이터 추가 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
        }
        return "redirect:/list"; // 데이터 추가 후 리스트 페이지로 리다이렉트
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
        System.out.println("aaaaaaa");
        return "edit";
    }

    @RequestMapping(value = "/edit_ok", method = RequestMethod.POST)
    public String editOk(CrudVO vo, @RequestParam("file") MultipartFile file, Model model) {
        try {
            String uploadPath = "/path/to/upload/directory/";

            if (!file.isEmpty()) {
                // 새 파일 업로드 처리
                String fileName = file.getOriginalFilename();
                File dest = new File(uploadPath + fileName);

                // 기존 파일 삭제
                CrudVO existingVO = crudDAO.getCrud(vo.getId());
                if (existingVO.getFilename() != null) {
                    File existingFile = new File(uploadPath + existingVO.getFilename());
                    if (existingFile.exists()) {
                        existingFile.delete();
                    }
                }

                // 새 파일 저장
                file.transferTo(dest);

                // VO에 새 파일 이름 설정
                vo.setFilename(fileName);
                System.out.println("새 파일 업로드 성공: " + fileName);
            } else {
                // 새 파일이 없으면 기존 파일 이름 유지
                CrudVO existingVO = crudDAO.getCrud(vo.getId());
                vo.setFilename(existingVO.getFilename());
            }

            // 업데이트 실행
            int i = crudDAO.updateCrud(vo);
            if (i == 0) {
                System.out.println("데이터 수정 실패");
            } else {
                System.out.println("데이터 수정 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "파일 수정 실패: " + e.getMessage());
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
