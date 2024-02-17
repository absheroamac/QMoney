package qmoney.portfolio.comparators;

import java.util.Comparator;

import qmoney.portfolio.dto.AnnualizedReturn;

public class AnnualizedReturnComparator implements Comparator<AnnualizedReturn> {

    @Override
    public int compare(AnnualizedReturn o1, AnnualizedReturn o2) {
        // TODO Auto-generated method stub
        return (int) (o1.getAnnualizedReturn() - o2.getAnnualizedReturn());
    }

}
