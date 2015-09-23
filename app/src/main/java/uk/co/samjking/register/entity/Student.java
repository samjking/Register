package uk.co.samjking.register.entity;

/**
 * Created by sking on 23/09/15.
 */
public class Student {

    private long mId;

    private String mFullName;

    private String mTutorGroup;

    public Student(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getTutorGroup() {
        return mTutorGroup;
    }

    public void setTutorGroup(String tutorGroup) {
        mTutorGroup = tutorGroup;
    }
}
