package spring;

import java.util.Date;

public class MemberRegisterService {
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
		System.out.println("MemberRegisterService(MemberDao)");
	}
	
	public void regist(RegisterRequest regReq) {
		System.out.println("MemberRegisterService regist에 들어옴");
		Member member = memberDao.selectByEmail(regReq.getEmail());
		if(member != null) {
			System.out.println("입력한 member는 널이 아니다");
			System.out.println(member.getEmail());
			System.out.println(member.getName());
			System.out.println(member.getPassword());
			System.out.println(member.getId());
			System.out.println(member.getRegisterDate());
			throw new AlreadyExistingMemberException(member.getEmail() + "는 이미 등록된 이메일입니다.");
		}
		Member newMember = new Member(
				regReq.getEmail(),
				regReq.getPassword(),
				regReq.getName(),
				new Date() //java.util.Date
				);
		memberDao.insert(newMember);
	}
}
