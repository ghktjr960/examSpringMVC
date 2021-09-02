package spring;

public class ChangePasswordService {

	private MemberDao memberDao;
	
	public ChangePasswordService() {
		System.out.println("ChangePasswordService()");
	}
	
	public void setMemberDao(MemberDao memberDao) {
		System.out.println("setMemberDao(memberDao)");
		this.memberDao = memberDao;
	}
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}
}
