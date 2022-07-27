package com.example.demo.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.WebMvcConfig;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
//@PropertySource("classpath:/application.properties")
@Log4j2
public class AuthController {
	
	private final AuthService as;
	private final UserInfoService uis;
	//private final ServletContext context;
	
	//회원가입 폼
		@RequestMapping(value="/signUp", method=RequestMethod.GET)
		public String signUpForm() {
			System.out.println("회원가입 폼 왔다");
			return "signUp";
		}
		
//		 // 파일 저장할 위치
//	    @Value("${file.path}")
//	    private String savePath;

		@PostMapping("/signUp")
		public String signUpOK(SignUpDTO signupDto, HttpServletRequest request) {
			System.out.println("회원가입ok폼 왔다");
			UserInfo user =signupDto.toEntity();
			//UUID uuid = UUID.randomUUID();
			//fileName = uuid+fileName1.substring(fileName1.lastIndexOf("."));
			// 실제 파일 저장 경로
			//String real = session.getServletContext().getRealPath("/resources/m_photo");
			// 파일 저장			
			
			//String path = session.getServletContext().getRealPath("image");
			//String path = request.getServletContext().getRealPath("resources/image/" + imageName + "." + extension);
			//String path = "";
			//System.out.println(path);
			



			if(signupDto.getUserFile() != null) {
				//path = context.getRealPath("/image/");
				try {
//					MultipartFile uploadFile = signupDto.getUserFile();
//					String fname=uploadFile.getOriginalFilename();
//					UUID uuid = UUID.randomUUID();
//					String fileName = uuid+fname.substring(fname.lastIndexOf("."));
//					File coverFile=new File(savePath,fileName);
//					uploadFile.transferTo(coverFile);
//					user.setUser_file(fileName);
//					FileOutputStream fos = new FileOutputStream(new File(savePath,fileName));
//					fos.write(uploadFile.getBytes());
//					fos.close();
					
					MultipartFile uploadFile = signupDto.getUserFile();
					String fname=uploadFile.getOriginalFilename();
					UUID uuid = UUID.randomUUID();
					String fileName = uuid+fname.substring(fname.lastIndexOf("."));
	                File converFile = new File("/Users/feelj/userImage", fileName);
	                uploadFile.transferTo(converFile);  //--- 저장할 경로를 설정 해당 경로는 각자 원하는 위치로 설정하면 됩니다. 다만, 해당 경
					user.setUser_file(fileName);
	                
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}else {
				user.setUser_file("");
			}
			
			System.out.println("유저파일명"+user.getUser_file());
			
			UserInfo userEntity = as.signup(user);
			return "redirect:/login";
		}
		
		@RequestMapping(value="/login", method=RequestMethod.GET)
		public String loginForm() {
			System.out.println("로그인 폼 왔다.");
			return "login";
		}
		
		@RequestMapping(value="/login", method=RequestMethod.POST)
		public String loginOK(LoginDTO loginDto) {
			System.out.println("로그인OK");
			return "main";
		}
		
		
		 /**
	     * 유저 페이지
	     * @param model
	     * @param authentication
	     * @return
	     */
	    @GetMapping("/")
	    public String userAccess(Model model, Authentication authentication) {
	        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
	        UserInfo user = (UserInfo)authentication.getPrincipal();  //userDetail 객체를 가져옴
	        model.addAttribute("user", user);      //유저 아이디
	        //session.setAttribute("user_num", user.getUser_num());
	    	return "user_access";
	    }
}