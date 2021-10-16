package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.BonusCard;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.BonusCardRepo;
import kg.inaiproject.booking.services.BonusCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonusCardServiceImpl implements BonusCardService {

    @Autowired
    private BonusCardRepo bonusCardRepo;

    @Override
    public BonusCard create(BonusCard bonusCard) {
        return bonusCardRepo.save(bonusCard);
    }

    @Override
    public List<BonusCard> findAll() {
        return bonusCardRepo.findAll();
    }

    @Override
    public BonusCard getById(Long id) {
        return bonusCardRepo.getById(id);
    }

    @Override
    public BonusCard update(BonusCard bonusCard) {
        return bonusCardRepo.findById(bonusCard.getId())
                .map(newBonusCard -> {
                    newBonusCard.setBalance(bonusCard.getBalance());
                    newBonusCard.setUser(bonusCard.getUser());
                    return bonusCardRepo.save(bonusCard);
                }).orElseThrow(() -> new RecordNotFoundException("Bonus card not found with id "+bonusCard.getId()));
    }
}
