package providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import models.Client;

public class ClientProvider {
    DatabaseReference mDatabase;

    public ClientProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user").child("Clients");
    }


    public Task<Void> create(Client client){
        Map<String,Object > map = new HashMap<>();
        map.put("name",client.getName());
        map.put("ine",client.getIne());
        map.put("direccion",client.getDireccion());
        map.put("email",client.getEmail());
        map.put("telefono",client.getTelefono());
        map.put("password",client.getPassword());

        return mDatabase.child(client.getId()).setValue(map);

    }
}

