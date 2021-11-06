package com.example.newera.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newera.R;
import com.example.newera.databinding.FragmentHomeBinding;
import com.example.newera.inter.OnItemClickListener;
import com.example.newera.ui.create.TaskAdapter;
import com.example.newera.ui.create.TaskModel;
import com.example.newera.utils.App;
import com.example.newera.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    ArrayList<TaskModel> list = new ArrayList<>();
    private TaskAdapter adapter = new TaskAdapter();
    NavController navController;
    TaskModel taskModel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.createTaskFragment);
            }
        });
        initAdapter();
        getDataFromFireBase(); //И СДЕСЬ
//        onLongDelete();
    }

    // Ошибка в этом методе
    private void getDataFromFireBase() {
        db.collection("userCollection").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        int color = document.getLong("color").intValue();
                        String image = (String) document.getData().get("image");
                        String time = (String) document.getData().get("time");
                        String title = (String) document.getData().get("title");
                        taskModel = new TaskModel(color, title, time, image);
                        list.add(taskModel);
                        Log.d("TAG", document.getId() + " => " + document.getData());
                    }
                    adapter.addList(list);
                } else {
                    Log.e("TAG", "Error getting documents.", task.getException());
                }
            }
        });
    }

    private void onLongDelete() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(TaskModel taskModel, int position) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Выбери действие");
                alertDialog.setMessage("Вы хотите удалить?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().taskDao().delete(taskModel);
                        adapter.delete(getDataFromDataBase());
                        Toast.makeText(getActivity(), "Удалено", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });

    }

    private ArrayList<TaskModel> getDataFromDataBase() {
        return (ArrayList<TaskModel>) App.getInstance().taskDao().getAll();
    }

    private void initAdapter() {
        binding.taskRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.taskRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}