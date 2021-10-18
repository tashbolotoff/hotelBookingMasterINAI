package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.Wallet;
import kg.inaiproject.booking.repos.WalletRepo;
import kg.inaiproject.booking.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepo walletRepo;

    @Override
    public Wallet create(Wallet wallet) {
        return walletRepo.save(wallet);
    }

    @Override
    public Wallet getById(Long id) {
        return walletRepo.getById(id);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepo.findAll();
    }

    @Override
    public Wallet getByUserId(Long id) {
        return walletRepo.getByUserId(id);
    }
}