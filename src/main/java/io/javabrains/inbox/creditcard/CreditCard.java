package io.javabrains.inbox.creditcard;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Table(value = "creditcards_by_user")
public class CreditCard {
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name = "bank_name", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String bankName;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String cardName;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String schemeName;

    @CassandraType(type = CassandraType.Name.INT)
    private int minMonthlySpending;

    @CassandraType(type = CassandraType.Name.DOUBLE)
    private double cashbackPercentage;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.TEXT)
    private List<String> bestSpendingCategory;

    public CreditCard() {
    }

    public CreditCard(String id, String bankName, String cardName, String schemeName, int minMonthlySpending, double cashbackPercentage, List<String> bestSpendingCategory) {
        this.id = id;
        this.bankName = bankName;
        this.cardName = cardName;
        this.schemeName = schemeName;
        this.minMonthlySpending = minMonthlySpending;
        this.cashbackPercentage = cashbackPercentage;
        this.bestSpendingCategory = bestSpendingCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public int getMinMonthlySpending() {
        return minMonthlySpending;
    }

    public void setMinMonthlySpending(int minMonthlySpending) {
        this.minMonthlySpending = minMonthlySpending;
    }

    public double getCashbackPercentage() {
        return cashbackPercentage;
    }

    public void setCashbackPercentage(double cashbackPercentage) {
        this.cashbackPercentage = cashbackPercentage;
    }

    public List<String> getBestSpendingCategory() {
        return bestSpendingCategory;
    }

    public void setBestSpendingCategory(List<String> bestSpendingCategory) {
        this.bestSpendingCategory = bestSpendingCategory;
    }
}
