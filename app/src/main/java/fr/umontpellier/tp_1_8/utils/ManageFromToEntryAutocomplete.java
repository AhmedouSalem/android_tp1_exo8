package fr.umontpellier.tp_1_8.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


import java.util.ArrayList;
import java.util.List;

import fr.umontpellier.tp_1_8.R;
import fr.umontpellier.tp_1_8.data.DataStatic;
import fr.umontpellier.tp_1_8.entities.City;

public class ManageFromToEntryAutocomplete {
    public static void manageFieldAutoCompleteFromAndTo(android. content. Context context, AutoCompleteTextView editTextDeparture , AutoCompleteTextView editTextArrival) {


        List<City> cities = DataStatic.getCitiesFromAPI();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line);
        for (City city : cities) {
            adapter.add(city.getName());
            adapter.add(city.getPostalCode());
        }

        editTextDeparture.setAdapter(adapter);
        editTextArrival.setAdapter(adapter);

        editTextDeparture.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterSuggestions(charSequence, editTextDeparture, editTextArrival, cities);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        editTextArrival.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterSuggestions(charSequence, editTextArrival, editTextDeparture, cities);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private static void filterSuggestions(CharSequence charSequence, AutoCompleteTextView editText, AutoCompleteTextView otherEditText, List<City> cities) {
        List<String> suggestions = new ArrayList<>();
        for (City city : cities) {
            if (city.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                suggestions.add(city.getName());
            } else if (city.getPostalCode().contains(charSequence.toString())) {
                suggestions.add(city.getName());
            }
        }

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) editText.getAdapter();
        adapter.clear();
        adapter.addAll(suggestions);
    }
}
