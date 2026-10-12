package org.example.ebankservice.controllers;

import org.example.ebankservice.entities.BankAccount;
import org.example.ebankservice.services.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbankController {
    private EbankService ebankService;

    public EbankController(EbankService ebankService) {
        this.ebankService = ebankService;
    }



    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts(){

        return ebankService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public  BankAccount getBankAccountById(@PathVariable String id){
        return ebankService.getBankAccountById(id);
    }

    @PostMapping("/accounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        return ebankService.save(bankAccount);
    }
}
