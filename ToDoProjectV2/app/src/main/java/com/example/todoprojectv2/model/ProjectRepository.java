package com.example.todoprojectv2.model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todoprojectv2.model.database.ProjectDatabase;
import com.example.todoprojectv2.model.database.ToDoDAO;
import com.example.todoprojectv2.model.retrofit.OpenWeatherAPI;
import com.example.todoprojectv2.model.retrofit.ServiceGenerator;
import com.example.todoprojectv2.model.retrofit.WeatherResponse;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {

    private static ProjectRepository instance;
    private ToDoDAO toDoDAO;
    private LiveData<List<ToDoModelEntity>> allToDos;
    private MutableLiveData<WeatherResponse.Main> allElements;

    private static final String apikey = "e69b4353426ba8adeb93de80fb504705";

    public ProjectRepository(Application application) {
        ProjectDatabase database = ProjectDatabase.getInstance(application);
        toDoDAO = database.toDoDAO();
        allToDos = toDoDAO.getAllToDos();
        allElements = new MutableLiveData<>();
    }

    public static ProjectRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ProjectRepository(application);
        }
        return instance;
    }

    public void insert(ToDoModelEntity toDoModelEntity) {
        new InsertToDoAsync(toDoDAO).execute(toDoModelEntity);
    }

    private static class InsertToDoAsync extends AsyncTask<ToDoModelEntity, Void, Void> {

        private ToDoDAO toDoDAO;

        private InsertToDoAsync(ToDoDAO toDoDAO) {
            this.toDoDAO = toDoDAO;
        }

        @Override
        protected Void doInBackground(ToDoModelEntity... toDoModelEntities) {
            toDoDAO.insert(toDoModelEntities[0]);
            return null;
        }
    }

    public LiveData<List<ToDoModelEntity>> getAllToDos() {
        return allToDos;
    }

    public void update(ToDoModelEntity toDoModelEntity) {
        new UpdateToDoAsync(toDoDAO).execute(toDoModelEntity);
    }

    private static class UpdateToDoAsync extends AsyncTask<ToDoModelEntity, Void, Void> {

        private ToDoDAO toDoDAO;

        private UpdateToDoAsync(ToDoDAO toDoDAO) {
            this.toDoDAO = toDoDAO;
        }

        @Override
        protected Void doInBackground(ToDoModelEntity... toDoModelEntities) {
            toDoDAO.update(toDoModelEntities[0]);
            return null;
        }
    }

    public void delete(ToDoModelEntity toDoModelEntity) {
        new DeleteToDoAsync(toDoDAO).execute(toDoModelEntity);
    }

    private static class DeleteToDoAsync extends AsyncTask<ToDoModelEntity, Void, Void> {

        private ToDoDAO toDoDAO;

        private DeleteToDoAsync(ToDoDAO toDoDAO) {
            this.toDoDAO = toDoDAO;
        }

        @Override
        protected Void doInBackground(ToDoModelEntity... toDoModelEntities) {
            toDoDAO.delete(toDoModelEntities[0]);
            return null;
        }
    }

    public MutableLiveData<WeatherResponse.Main> getAllElements(String city) {
        requestElements(city);
        return allElements;
    }

    public void requestElements(String city) {
        OpenWeatherAPI weatherAPI = ServiceGenerator.getWeatherAPI();

        Call<WeatherResponse> call = weatherAPI.getWeather(city, apikey, "metric");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    allElements.setValue(response.body().main);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.i("Retrofit", t.toString());
            }
        });
    }


}
