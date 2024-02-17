package qmoney.portfolio.comparators;

import java.util.Comparator;

import qmoney.portfolio.dto.ClosingPrice;

public class ClosingPriceComparator implements Comparator<ClosingPrice> {

    @Override
    public int compare(ClosingPrice o1, ClosingPrice o2) {
        // TODO Auto-generated method stub
        return (int) (o1.getClosingPrice() - o2.getClosingPrice());
    }

}
