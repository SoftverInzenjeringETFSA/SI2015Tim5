package ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import java.util.List;

import javax.persistence.*;

@Entity
public enum Status {
primljena, uPripremi, naDostavi, dostavljena
}
