package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Wallet;
import fpt.swp.WorkSpace.repository.UserRepo;
import fpt.swp.WorkSpace.repository.WalletRepository;
import fpt.swp.WorkSpace.response.TopUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepo userRepository;

    public Wallet topUpWallet(TopUpRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        int newAmount = wallet.getAmount() + request.getAmount();
        wallet.setAmount(newAmount);

        return walletRepository.save(wallet);
    }
}
