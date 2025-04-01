package pageEvents;

public interface AccountRegisterEvent {
	String FIRSTNAME="//input[@id='input-firstname']";
	String LASTNAME="//input[@id='input-lastname']";
	String EMAIL="//input[@id='input-email']";
	String TELEPHONE="//input[@id='input-telephone']";
	String PASSWORD="//input[@id='input-password']";
	String CONFIRM="//input[@id='input-confirm']";
	String PRIVACY="//input[@name='agree']";
	String CONTINUE="//input[@value='Continue']";
	String MESSAGE="//h1[text()='Your Account Has Been Created!']";
}
