package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private Period period;
    @Embedded
    private Address address;

    // 값 타입 컬렉션이 단순한 경우에는 사용 가능.
    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns = @JoinColumn(name="id"))
    private Set<String> favoriteFoods = new HashSet<>();

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    /* 값 타입 컬렉션은 기본적으로 지연로딩. 실무에선 값 타입 컬렉션을 잘 사용 X. 주인과 관련된 데이터를 모두 삭제하고 다시 insert 하는 등 관련해서 복잡한 case 들이 등장할 수 있음
       1:N 매핑을 통해서 해결하는 방향이 더 좋음
     */
    @ElementCollection
    @CollectionTable(name="ADDRESSHISTORY", joinColumns = @JoinColumn(name="id"))
    private List<Address> addressHistory = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
