package android.example.navigationdrawer;

public class UploadPDF {

    public String filename;
    public String url;
    public String branch;
    public String semester;

    public UploadPDF(){};


    public UploadPDF(String filename, String url, String branch, String semester){
        this.filename=filename;
        this.url=url;
        this.branch = branch;
        this.semester = semester;
    }

    public String getFilename() {
        return filename;
    }

    public String getUrl() {
        return url;
    }

    public String getBranch() {
        return branch;
    }

    public String getSemester(){
        return semester;
    }
}
