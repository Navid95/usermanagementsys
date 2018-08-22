package navid.usermanagementsys.service.Security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

//    private StrongPasswordEncryptor strongPasswordEncryptor;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    @Autowired
//    public void setStrongPasswordEncryptor(StrongPasswordEncryptor strongPasswordEncryptor) {
//        this.strongPasswordEncryptor = strongPasswordEncryptor;
//    }

    @Override
    public String encryptString(String input) {
        return bCryptPasswordEncoder.encode(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(plainPassword,encryptedPassword);
    }
}
