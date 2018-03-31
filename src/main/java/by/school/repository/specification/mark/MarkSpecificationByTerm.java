package by.school.repository.specification.mark;

import by.school.entity.Mark;
import by.school.entity.Term;
import by.school.entity.enums.DayOfWeekEnum;
import by.school.entity.enums.MarkType;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

import static by.school.service.impl.TermService.MILLISECONDS_IN_DAY;


public class MarkSpecificationByTerm extends MarkSpecification {

    private Date dateFrom;
    private Date dateTo;
//    private Integer termNumber;

    public MarkSpecificationByTerm(Term term) {
        this.dateFrom = term.getStart();
        this.dateTo = new Date(term.getEnd().getTime()+MILLISECONDS_IN_DAY);
//        this.termNumber = term.getNumber();
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.or(
                Restrictions.or(
                        Restrictions.eq("type", MarkType.term.toString()),
                        Restrictions.eq("type", MarkType.year.toString())),
                Restrictions.between("date",dateFrom,dateTo));
//        return Restrictions.or(
//                Restrictions.or(
//                        Restrictions.eq("termNumber", termNumber),
//                        Restrictions.or(
//                                Restrictions.eq("type", term),
//                                Restrictions.eq("type", year))),
//                Restrictions.between("date", dateFrom, dateTo));
    }

    @Override
    public boolean specified(Mark mark) {
        Date date = mark.getDate();
        return (
//                        mark.getTermNumber().intValue() == termNumber &&
                MarkType.valueOf(mark.getType()) == MarkType.term)
                || MarkType.valueOf(mark.getType()) == MarkType.year
                || date.equals(dateFrom)
                || date.equals(dateTo)
                || (date.after(dateFrom) && date.before(dateTo));
    }
}
