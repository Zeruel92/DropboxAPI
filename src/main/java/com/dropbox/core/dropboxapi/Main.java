import com.dropbox.core.*;
import com.dropbox.core.v2.*;
import com.dropbox.core.v2.DbxFiles.Metadata;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

class Main {
    static final String ACCESS_TOKEN = "insert_here_token";

    public static void main(String args[]) throws DbxUsers.GetCurrentAccountException, DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        DbxUsers.FullAccount account = client.users.getCurrentAccount();
        System.out.println(account.name.displayName);

        // Get files and folder metadata from Dropbox root directory
        List<Metadata> entries = client.files.listFolder("").entries;
        
        for (DbxFiles.Metadata metadata : entries) {
            System.out.println(metadata.name);
        }

       // Upload "test.txt" to Dropbox
        InputStream in = new FileInputStream("test.txt");
        DbxFiles.FileMetadata metadata = client.files.uploadBuilder("/test.txt").run(in);
    }
}