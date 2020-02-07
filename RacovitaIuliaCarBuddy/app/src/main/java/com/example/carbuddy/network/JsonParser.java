package com.example.carbuddy.network;

import com.example.carbuddy.Expense;
import com.example.carbuddy.ExpenseDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static HttpResponse parseJson (String json){
        if (json == null) {
            return null;
        }
        try{
            JSONObject  object = new JSONObject(json);
            List<Expense> expense = getItemListFromJson(object.getJSONArray("expense"));
            return new HttpResponse(expense);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



    private static List<Expense> getItemListFromJson(JSONArray array)
            throws JSONException {
        if (array == null) {
            return null;
        }
        List<Expense> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Expense expense = getItemFromJson(array.getJSONObject(i));
            if (expense != null) {
                results.add(expense);
            }
        }
        return results;
    }

    private static Expense getItemFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        Integer cost = object.getInt("cost");
        String place = object.getString("place");
        ExpenseDetails expenseDetails = getExpenseDetailsFromJson(object.
                getJSONObject("expenseDetails"));
        return new Expense(cost, place, expenseDetails);
    }

    private static ExpenseDetails getExpenseDetailsFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        String type = object.getString("type");
        String reason = object.getString("reason");
        String furtherObservations = object.getString("furtherObservations");

        return new ExpenseDetails(type, reason, furtherObservations);
    }



}
