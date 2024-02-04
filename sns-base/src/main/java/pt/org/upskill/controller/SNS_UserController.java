package pt.org.upskill.controller;

import pt.org.upskill.domain.SNSUser;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.SNSUserRepository;

import java.util.List;

public class SNS_UserController implements  UIable{

    SNSUserRepository snsUserRepository = new SNSUserRepository();
    private SNSUser snsUser;
    @Override
    public void register(DTO dto) {
        snsUser = snsUserRepository.createSNSUser(dto);
    }

    @Override
    public boolean save() {
        try{
            snsUserRepository.save(snsUser);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<KeyValueDTO> keyValueDTOList() {
        return snsUserRepository.keyValueDTOList();
    }
}
