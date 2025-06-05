package com.cos.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

//html 파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired	//의존성 주입
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
	    Optional<User> user = userRepository.findById(id);
	    if (user.isEmpty()) {
	        return "삭제에 실패하였습니다. 해당 아이디는 DB에 없습니다.";
	    }

	    userRepository.deleteById(id);
	    return "삭제되었습니다 id : " + id;
	}
	
	//delete
//	@DeleteMapping("/dummy/user/{id}")
//	public String delete(@PathVariable int id) {
//		try {
//			userRepository.deleteById(id);
//		} catch (Exception e) {
//			return "삭제에 실패하였습니다 해당 아이디는 DB에 없습니다.";
//		}
//		
//		
//		return "삭제되었습니다 id :" + id;
//	}
	
	
	
	//save함수는 id를 전달하지 않으면 insert
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
	
	
	//email, password 
	@Transactional	//함수 종료시 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {	
		//Json 데이터를 요청 => java Object(MessageConverter의 Jackson라이브러리가 @RequestBody를 통해변환해서 받아줘요.
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("수정에 실패하였습니다.")) ;
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		//더티 체킹 = Transactional이 종료될때 set 등 있으면 변경감지, 데이터베이스에 수정을 보내줌. userRepository.save(user); 사용 안해도 됨
		//더티 체킹은 = JPA는 변경을 감지하는 것/ DB에서는 트랜잭션일어난 일을 한번에  Commit
		return user;
	}
	
	
	//모든 유저 조회
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건에 데이터를 리턴 (페이징 처리)
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser =  userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	//유저 한명 조회
	//{id} 주소로 파라미터를 전달 받을 수 있음
	// http:localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User Detail(@PathVariable int id) {
		
		// user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐
		//그럼 return 할때 null 이 리턴이 되잖아 그럼 프로그램에 문제가 있지 않겠지 ?
		//Optional로 너의 User 객체를 감싸서 가져 올테니 null인지 아닌지 판단해서 return해가 아래 코드
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
			}
		});
		//요청 = 웹 브라우저
		//user 객체 = 자바 오브젝트
		//변환 ( 웹 브라우저가 이해할 수 있는 데이터) -> Json
		//스프링 부트 = MessageConverter라는 애가 응답시에 자동 작동
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		//User 오브젝트를 Json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {	//key = value(약속된 규칙)
		
		System.out.println("username :" + user.getUsername());
		System.out.println("password :" + user.getPassword());
		System.out.println("email :" + user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return"회원가입이 완료되었습니다.";
	}
}
