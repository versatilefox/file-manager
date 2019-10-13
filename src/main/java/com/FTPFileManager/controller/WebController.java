package com.FTPFileManager.controller;

import com.FTPFileManager.GlobalProperties;
import com.FTPFileManager.model.Note;
import com.FTPFileManager.service.NoteService;
import com.FTPFileManager.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.*;

@Controller
public class WebController {

    @Autowired
    Service service;

    @Autowired
    NoteService noteService;

    @Autowired
    private GlobalProperties globalProperties;

    WebController(Service service, NoteService noteService, GlobalProperties globalProperties) {
        this.service = service;
        this.noteService = noteService;
        this.globalProperties = globalProperties;
    }

    @GetMapping("/addFile")
    String getTXTPage(){
        return "createTxt";
    }

    @PostMapping("/addFile")
    String saveTxt(@RequestParam("text") String text,
                   @RequestParam("name") String name,
                   @RequestParam("path") String path){
        service.createTxt(name, path, text);
        return "/list";

    }

    @GetMapping("/editTxt")
    ModelAndView editTxt(@RequestParam("nextFolder") String path){
        ModelAndView modelAndView = new ModelAndView("editTxt");
        modelAndView.addObject("file", service.getFileByPath(path));
        modelAndView.addObject("text", service.getText(path));
        return modelAndView;
    }

    @PostMapping("/editTxt")
    ModelAndView editFile(@RequestParam("text") String text,
                          @RequestParam("name") String name,
                          @RequestParam("path") String path){
        if (path.endsWith(name + ".txt")) path = path.substring(0, path.lastIndexOf("/"));
        service.createTxt(name, path, text);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("nextFolder", path);
        return modelAndView;
    }

    @GetMapping("/list/seeNote")
    ModelAndView seeNote(@RequestParam(value = "nextFolder") String path){
        ModelMap m = new ModelMap();
        m.addAttribute("nextFolder", path);
        ModelAndView modelAndView = new ModelAndView("notes", m);
        Note note = noteService.find(path);
        if (note == null) modelAndView.addObject("emptyNote", true);
        else {
            modelAndView.addObject("emptyNote", false);
            modelAndView.addObject("note", note);
        }
        return modelAndView;
    }

    @PostMapping("/list/seeNote")
    ModelAndView addNote(@RequestParam("message") String message,
                         @RequestParam("nextFolder") String path){
        noteService.create(path, message, new Date(System.currentTimeMillis()));
        ModelMap m = new ModelMap();
        m.addAttribute("nextFolder", path.substring(0, path.lastIndexOf("/")));
        return new ModelAndView("redirect:/list", m);
    }


    @GetMapping("/index")
    ModelAndView getin(@RequestParam(value = "nextFolder", required = false) String path){
        ModelAndView modelAndView = new ModelAndView("index");
        Map<String, String> map = new HashMap<>();
        for (File f : service.getAll(path)) {
            map.put(f.getName(), f.getPath());
        }
        List<File> list = service.getAll(path);
        modelAndView.addObject("files", list);
        modelAndView.addObject("isFile", list);
        return modelAndView;

    }

    @GetMapping("/list/download")
    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("nextFolder") String path) throws IOException {

        File file = new File(path);
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {

                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }


    @RequestMapping(value="/list/upload", method=RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("note") String note,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "nextFolder") String path,
                                   ModelMap model){
        if (path == null || path.length() < globalProperties.getRootDirectory().length()){
            path = globalProperties.getRootDirectory();
        }
        if (file.isEmpty()) {
            model.addAttribute("nextFolder", path);
            return new ModelAndView("redirect:/list", model);
        }
        if (service.uploadFile(file, path)){
            noteService.create(path+"/" + file.getOriginalFilename(), note, new Date(System.currentTimeMillis()));
            model.addAttribute("nextFolder", path);
            return new ModelAndView("redirect:/list", model);
        }
        model.addAttribute("nextFolder", path);
        return new ModelAndView("redirect:/list", model);
    }

    @RequestMapping(value = "list/addFolder", method = RequestMethod.GET)
    public ModelAndView addFolder(@RequestParam(value = "note") String note,
                                  @RequestParam(value = "nextFolder") String path,
                                  @RequestParam(value = "name") String name,
                                  ModelMap model){
        if (path == null || path.length() < globalProperties.getRootDirectory().length()){
            path = globalProperties.getRootDirectory();
        }
        service.createDirectory(path, name);
        noteService.create(path+"/" + name, note, new Date(System.currentTimeMillis()));
        model.addAttribute("nextFolder", path+"/" + name);
        return new ModelAndView("redirect:/list", model);
    }


    @GetMapping("/list/delete")
    ModelAndView deleteDirectory(@RequestParam(value = "deleted", required = true) String path, ModelMap model) throws IOException {
        service.deleteFileByPath(path);
        noteService.delete(path);
        model.addAttribute("nextFolder", path.substring(0, path.lastIndexOf("/")));
        return new ModelAndView("redirect:/list", model);
    }

    @RequestMapping("/list")
    ModelAndView getAll(@RequestParam(value = "nextFolder", required = false) String path){
        if (path == null || path.length() < globalProperties.getRootDirectory().length()){
            path = globalProperties.getRootDirectory();
        }
        List<File> list = service.getAll(path);
        ModelMap modelMap1 = new ModelMap();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("document", globalProperties.getDocumentImage());
        modelAndView.addObject("folder", globalProperties.getDirectoryImage());
        modelAndView.addObject("files", list);
        modelAndView.addObject("nextFolder", path);
        return modelAndView;
    }







}