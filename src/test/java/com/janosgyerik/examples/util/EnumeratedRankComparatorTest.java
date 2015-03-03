package com.janosgyerik.examples.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EnumeratedRankComparatorTest {
    enum Rating {
        AAA_PLUS("AAA+"),
        AAA("AAA"),
        AAA_MINUS("AAA-"),
        AA_PLUS("AA+"),
        AA("AA"),
        AA_MINUS("AA-"),
        BB("BB"),
        NR("Non-rated")
        ;

        private final String label;

        Rating(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private static class RatingComparator implements Comparator<Rating> {
        private final EnumeratedRankComparator<Rating> comparator;

        public RatingComparator() {
            comparator = EnumeratedRankComparator.fromHighToLow(Arrays.asList(Rating.values()));
        }

        @Override
        public int compare(Rating o1, Rating o2) {
            return comparator.compare(o1, o2);
        }
    }

    private final RatingComparator comparator = new RatingComparator();

    @Test
    public void test_AA_lessThan_AAA() {
        assertEquals(-1, comparator.compare(Rating.AA, Rating.AAA));
    }

    @Test
    public void test_AAA_lessThan_AAA_PLUS() {
        assertEquals(-1, comparator.compare(Rating.AAA, Rating.AAA_PLUS));
    }

    @Test
    public void test_AAA_greaterThan_AAA_MINUS() {
        assertEquals(1, comparator.compare(Rating.AAA, Rating.AAA_MINUS));
    }

    @Test
    public void test_AAA_equals_AAA() {
        assertEquals(0, comparator.compare(Rating.AAA, Rating.AAA));
    }

    @Test
    public void test_NR_lessThan_A_MINUS() {
        assertEquals(-1, comparator.compare(Rating.NR, Rating.AAA));
    }

    @Test
    public void test_sort_AA_AAA_NR_BB() {
        List<Rating> ratings = Arrays.asList(Rating.AA, Rating.AAA, Rating.NR, Rating.BB);
        Collections.sort(ratings, comparator);
        assertEquals(Arrays.asList(Rating.NR, Rating.BB, Rating.AA, Rating.AAA), ratings);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_inconsistent_ranks() {
        EnumeratedRankComparator.fromHighToLow(Arrays.asList(Rating.AA, Rating.AA_MINUS, Rating.AA));
    }

    @Test
    public void test_sortIssuersByRating() {
        class Issuer {
            final Rating rating;

            Issuer(Rating rating) {
                this.rating = rating;
            }

            @Override
            public String toString() {
                return "I-" + rating;
            }
        }

        Issuer issuerBB = new Issuer(Rating.BB);
        Issuer issuerAA = new Issuer(Rating.AA);
        Issuer issuerNR = new Issuer(Rating.NR);

        List<Issuer> issuers = Arrays.asList(issuerBB, issuerAA, issuerNR);
        Collections.sort(issuers, new Comparator<Issuer>() {
            @Override
            public int compare(Issuer o1, Issuer o2) {
                return comparator.compare(o1.rating, o2.rating);
            }
        });

        assertEquals(Arrays.asList(issuerNR, issuerBB, issuerAA), issuers);
    }
}