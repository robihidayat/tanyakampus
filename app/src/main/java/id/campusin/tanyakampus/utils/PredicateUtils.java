package id.campusin.tanyakampus.utils;

import android.os.Bundle;

import java.util.function.Predicate;

public class PredicateUtils {

    public final Predicate<String> valueNotNullOrEmpty() {
        return e -> e != null && !e.isEmpty();
    }

    public final Predicate<Bundle> valueNotNullOrEmptyBundle() {
        return e -> e != null && !e.isEmpty();
    }

}
