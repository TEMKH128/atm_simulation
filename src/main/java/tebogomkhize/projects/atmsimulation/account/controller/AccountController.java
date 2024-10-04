package tebogomkhize.projects.atmsimulation.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tebogomkhize.projects.atmsimulation.account.model.Account;
import tebogomkhize.projects.atmsimulation.account.model.dto.AccountDTO;
import tebogomkhize.projects.atmsimulation.account.model.dto.ResponseDTO;
import tebogomkhize.projects.atmsimulation.account.service.AccountService;

@RestController
@RequestMapping("api/v1/atmsim")
public class AccountController {
    private AccountService accService;

    @Autowired
    public AccountController(AccountService accService) {
        this.accService = accService;
    }

    @PostMapping("/account")
    public ResponseDTO createAccount(@RequestBody AccountDTO newAcc) {
        return accService.createAccount(
            newAcc.getName(), newAcc.getAge(), newAcc.getEmail());
    }


}