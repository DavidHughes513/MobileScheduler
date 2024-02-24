package Hughes.termscheduler.dataBase;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Hughes.termscheduler.dao.AssessmentsDAO;
import Hughes.termscheduler.dao.CoursesDAO;
import Hughes.termscheduler.dao.InstructorsDAO;
import Hughes.termscheduler.dao.NotesDAO;
import Hughes.termscheduler.dao.TermDAO;
import Hughes.termscheduler.entities.Assessments;
import Hughes.termscheduler.entities.Courses;
import Hughes.termscheduler.entities.Instructors;
import Hughes.termscheduler.entities.Notes;
import Hughes.termscheduler.entities.Term;
import kotlinx.coroutines.flow.Flow;

public class Repository {
    private TermDAO mTermDAO;
    private CoursesDAO mCoursesDAO;
    private AssessmentsDAO mAssessmentDAO;
    private InstructorsDAO mInstructorsDAO;
    private NotesDAO mNotesDAO;

    private List<Term> mAllTerms;
    private List<Courses> mAllCourses;
    private List<Assessments> mAllAssessments;
    private List<Instructors> mAllInstructors;
    private List<Notes> mAllNotes;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        TermDatabaseBuilder db = TermDatabaseBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCoursesDAO = db.coursesDAO();
        mAssessmentDAO = db.assessmentDAO();
        mInstructorsDAO = db.instructorsDAO();
        mNotesDAO= db.notesDAO();
    }

    //========================= TERMS FUNCTIONS ===========================
    public List<Term> getmAllTerms(){
        databaseExecutor.execute(() -> {
            mAllTerms = mTermDAO.getAllTerms();
        });

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return mAllTerms;
    }

    public void deleteAllTerms(){
        databaseExecutor.execute(() -> {
            mTermDAO.deleteAllTerms();
        });
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    public void insertTerm(Term term){
        databaseExecutor.execute(() -> {
            mTermDAO.insert(term);
        });
       /* try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }
    public void updateTerm(Term term){
        databaseExecutor.execute(() -> {
            mTermDAO.update(term);
        });
       /* try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }
    public void deleteTerm(Term term){
        databaseExecutor.execute(() -> {
            mTermDAO.delete(term);
        });
    }

    //========================= COURSES FUNCTIONS ===========================

    public List<Courses> getmAllCourses(){
        databaseExecutor.execute(() -> {
            mAllCourses = mCoursesDAO.getAllCourses();
        });

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return mAllCourses;
    }

    public void deleteAllCourses(){
        databaseExecutor.execute(() -> {
            mCoursesDAO.deleteAllCourses();
        });
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public void insertCourse(Courses course){
        databaseExecutor.execute(() -> {
            mCoursesDAO.insert(course);
        });
        /*try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }
    public void updateCourse(Courses course){
        databaseExecutor.execute(() -> {
            mCoursesDAO.update(course);
        });
       /* try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }
    public void deleteCourse(Courses course){
        databaseExecutor.execute(() -> {
            mCoursesDAO.delete(course);
        });
    }

    //========================= ASSESSMENT FUNCTIONS ===========================

    public List<Assessments> getmAllAssessments(){
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return mAllAssessments;
    }

    public void insertAssessments(Assessments assessments){
        databaseExecutor.execute(() -> {
            mAssessmentDAO.insert(assessments);
        });
       /* try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }
    public void updateAssessments(Assessments assessments){
        databaseExecutor.execute(() -> {
            mAssessmentDAO.update(assessments);
        });
        /*try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }
    public void deleteAssessments(Assessments assessments){
        databaseExecutor.execute(() -> {
            mAssessmentDAO.delete(assessments);
        });
    }


    //========================= INSTRUCTOR FUNCTIONS ===========================
    public List<Instructors> getmAllInstructors(){
        databaseExecutor.execute(() -> {
            mAllInstructors = mInstructorsDAO.getAllInstructors();
        });

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return mAllInstructors;
    }

    public void insertInstructors(Instructors instructors){
        databaseExecutor.execute(() -> {
            mInstructorsDAO.insert(instructors);
        });
    }
    public void updateInstructors(Instructors instructors){
        databaseExecutor.execute(() -> {
            mInstructorsDAO.update(instructors);
        });
    }
    public void deleteInstructors(Instructors instructors){
        databaseExecutor.execute(() -> {
            mInstructorsDAO.delete(instructors);
        });
    }


    public List<Notes> getmAllNotes(){
        databaseExecutor.execute(() -> {
            mAllNotes = mNotesDAO.getAllNotes();
        });

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return mAllNotes;
    }

    public void insertNotes(Notes notes){
        databaseExecutor.execute(() -> {
            mNotesDAO.insert(notes);
        });
        /*try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }
    public void updateNotes(Notes notes){
        databaseExecutor.execute(() -> {
            mNotesDAO.update(notes);
        });
       /* try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }
    public void deleteNotes(Notes notes){
        databaseExecutor.execute(() -> {
            mNotesDAO.delete(notes);
        });
    }

}
