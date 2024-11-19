package devandroid.misael.applistacurso.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import devandroid.misael.applistacurso.R;
import devandroid.misael.applistacurso.controller.CourseController;
import devandroid.misael.applistacurso.model.Course;

public class CustomAdapter extends ArrayAdapter<Course> {
    private final Context ctx;
    private final List<Course> courses;
    private final CourseController courseController;

    public CustomAdapter(Context context, List<Course> courses, CourseController courseController) {
        super(context, R.layout.list_item, courses);
        this.ctx = context;
        this.courses = courses;
        this.courseController = courseController;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.list_item, parent, false);
        }

        TextView itemText = convertView.findViewById(R.id.item_text);
        ImageView deleteIcon = convertView.findViewById(R.id.delete_icon);

        itemText.setText(courses.get(position).getName());

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseController.deleteCourse(courses.get(position).getId(), ctx);
                courses.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
