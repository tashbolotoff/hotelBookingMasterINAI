package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.BonusCard;

import java.util.List;

public interface BonusCardService {

    BonusCard create(BonusCard bonusCard);

    List<BonusCard> findAll();

    BonusCard getById(Long id);

    BonusCard update(BonusCard bonusCard);
}
