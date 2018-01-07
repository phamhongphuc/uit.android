package uit.group.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
/*    private void InitializeMessagesRecyclerView() {
        MessageRecyclerViewAdapter adapter = new MessageRecyclerViewAdapter();
        RecyclerView recyclerView = findViewById(R.id.list_messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void InitializeDataBinding() {
        ActivityProjectDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        binding.setSocket(_Socket.State.getInstance());

        binding.setState(state);
        binding.setGlobal(Global.getInstance());
    }*/
}
