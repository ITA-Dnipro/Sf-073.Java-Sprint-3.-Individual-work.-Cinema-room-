package antifraud.model;

import antifraud.annotation.RegionConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter@Setter@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long transactionId;
    @Positive
    @NotNull
    Long amount;
    @NotEmpty
    String ip;
    @NotEmpty
    String number;
    @RegionConstraint
    String region;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime date;

    String result;


    String feedback;

    public String getFeedback() {
        return Objects.requireNonNullElse(feedback, "");
    }

    public Transaction(Long amount) {
        this.amount = amount;
    }

    public Transaction() {

    }
}
