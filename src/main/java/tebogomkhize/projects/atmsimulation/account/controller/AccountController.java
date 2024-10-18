package tebogomkhize.projects.atmsimulation.account.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import tebogomkhize.projects.atmsimulation.account.model.dto.AccountDTO;
import tebogomkhize.projects.atmsimulation.account.model.dto.ResponseDTO;
import tebogomkhize.projects.atmsimulation.account.service.AccountService;


@RestController
@RequestMapping("api/v1/atmsim/account")
public class AccountController {
    private AccountService accService;

    @Autowired
    public AccountController(AccountService accService) {
        this.accService = accService;
    }

    @PostMapping("")
    public ResponseDTO createAccount(@RequestBody AccountDTO newAcc) {
        return this.accService.createAccount(
            newAcc.getFirstName(), newAcc.getLastName(),
            newAcc.getAge(), newAcc.getEmail());
    }

    @GetMapping("/{accountNum}/balance")
    public ResponseDTO getAccBal(@PathVariable String accountNum) {
        return this.accService.getAccBal(accountNum);
    }

    @PutMapping("/{accountNum}/deposit/{amount}")
    public ResponseDTO depositMoney(
        @PathVariable String accountNum, @PathVariable float amount) {

        return this.accService.depositMoney(accountNum, amount);
    }

    @PutMapping("/{accountNum}/withdraw/{amount}")
    public ResponseDTO withdrawMoney(
        @PathVariable String accountNum, @PathVariable float amount) {

        return this.accService.withdrawMoney(accountNum, amount);
    }

    @PutMapping("/{accountNum}/transfer/{transferAcc}/{amount}")
    public ResponseDTO transferMoney(
        @PathVariable String accountNum, @PathVariable String transferAcc,
            @PathVariable float amount) {

        return this.accService.transferMoney(accountNum, transferAcc, amount);
    }

    @GetMapping("/{accountNum}/statement")
    public ResponseEntity<byte[]> getAccStatement(@PathVariable String accountNum) throws DocumentException {
        return this.accService.getAccStatement(accountNum);
    }
}