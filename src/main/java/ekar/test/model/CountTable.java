package ekar.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class CountTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer count;
    private Timestamp saveTime;

    public CountTable(Integer count){
        this.count = count;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Timestamp getSaveTime() {
        return saveTime;
    }

    private void setSaveTime(Timestamp saveTime) {
        this.saveTime = new Timestamp(System.currentTimeMillis());
    }
}
