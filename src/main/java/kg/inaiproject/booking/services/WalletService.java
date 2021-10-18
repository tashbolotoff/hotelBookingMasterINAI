package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.Wallet;

import java.util.List;

public interface WalletService {

    Wallet create(Wallet wallet);

    Wallet getById(Long id);

    List<Wallet> findAll();

    Wallet getByUserId(Long id);
}
