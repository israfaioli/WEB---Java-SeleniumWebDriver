package steps.login;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.LoginPage;
import utils.EnvironmentProperties;

public class LoginSteps {

    private static final String userEmail = EnvironmentProperties.getValue("userEmail");
    private static final String userPassword = EnvironmentProperties.getValue("userPassword");
    private final String expectedUserName = "automation test";


    //-------------------------- DADO --------------------------//
    @Dado("que eu acesso a url de login")
    public void que_eu_acesso_a_url_de_login() {
        LoginPage.visitloginUrl();
    }

    @Dado("preencho email e senha")
    public void preencho_email_e_senha() {
        LoginPage.inputLoginCredentials(userEmail, userPassword);
    }

    //-------------------------- QUANDO --------------------------//

    @Quando("eu clico no botão entrar")
    public void eu_clico_no_botão_entrar() {
        LoginPage.clickSubmitLogin();
    }

    @Quando("não preencho {string} e ou {string} corretamente")
    public void não_preencho_e_ou_corretamente(String userEmail, String userPassword) {
        LoginPage.inputLoginCredentials(userEmail, userPassword);

    }

    //-------------------------- ENTÃO --------------------------//

    @Entao("devo ser redirecionado à página de minha conta e apresentar o nome do usuário logado corretamente")
    public void devo_ser_redirecionado_à_página_de_minha_conta_e_apresentar_o_nome_do_usuário_logado_corretamente() {
        LoginPage.checkLoginUserName(expectedUserName);
    }

    @Então("deve ser uma mensagem bloqueando acesso {string}")
    public void deve_ser_uma_mensagem_bloqueando_acesso(String message) {
        Assert.assertEquals(message, LoginPage.getErrorMessage());
    }

}

