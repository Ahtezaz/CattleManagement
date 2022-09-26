package com.app.cattlemanagement.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    private String breed = "";
    private String childNo = "";
    private String dateOfBirth = "";
    private String dateOfFarm = "";
    private String desc = "";
    private String fatherTagNo = "";
    private String firstVaccineDate = "";
    private String firstVaccineName = "";
    private String fourthVaccineDate = "";
    private String fourthVaccineName = "";
    private String gender = "";
    private String imageUri = "";
    private String isFullyVaccinated = "";
    private String isPregnant = "";
    private String motherTagNo = "";
    private String name = "";
    private String obtainedSource = "";
    private String pregnantMonth = "";
    private String remainingVaccine = "";
    private String rfirstVaccineDate = "";
    private String rfirstVaccineName = "";
    private String rsecondVaccineName = "";
    private String rsecondVaccineDate = "";
    private String rthirdVaccineDate = "";
    private String rthirdVaccineName = "";
    private String secondVaccineDate = "";
    private String secondVaccineName = "";
    private String tagNo = "";
    private String thirdVaccineDate = "";
    private String thirdVaccineName = "";
    private String vaccineComplete = "";
    private String vaccineDesc = "";
    private String healthStatus = "";

    public Animal() {
    }

    protected Animal(Parcel in) {
        breed = in.readString();
        childNo = in.readString();
        dateOfBirth = in.readString();
        dateOfFarm = in.readString();
        desc = in.readString();
        fatherTagNo = in.readString();
        firstVaccineDate = in.readString();
        firstVaccineName = in.readString();
        fourthVaccineDate = in.readString();
        fourthVaccineName = in.readString();
        gender = in.readString();
        imageUri = in.readString();
        isFullyVaccinated = in.readString();
        isPregnant = in.readString();
        motherTagNo = in.readString();
        name = in.readString();
        obtainedSource = in.readString();
        pregnantMonth = in.readString();
        remainingVaccine = in.readString();
        rfirstVaccineDate = in.readString();
        rfirstVaccineName = in.readString();
        rsecondVaccineName = in.readString();
        rsecondVaccineDate = in.readString();
        rthirdVaccineDate = in.readString();
        rthirdVaccineName = in.readString();
        secondVaccineDate = in.readString();
        secondVaccineName = in.readString();
        tagNo = in.readString();
        thirdVaccineDate = in.readString();
        thirdVaccineName = in.readString();
        vaccineComplete = in.readString();
        vaccineDesc = in.readString();
        weight = in.readString();
        refDoc = in.readString();
        healthStatus = in.readString();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getBreed() {
        return breed;
    }

    public String getChildNo() {
        return childNo;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfFarm() {
        return dateOfFarm;
    }

    public String getDesc() {
        return desc;
    }

    public void setRefDoc(String refDoc) {
        this.refDoc = refDoc;
    }

    public String getFatherTagNo() {
        return fatherTagNo;
    }

    public String getFirstVaccineDate() {
        return firstVaccineDate;
    }

    public String getFirstVaccineName() {
        return firstVaccineName;
    }

    public String getFourthVaccineDate() {
        return fourthVaccineDate;
    }

    public String getFourthVaccineName() {
        return fourthVaccineName;
    }

    public String getGender() {
        return gender;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getIsFullyVaccinated() {
        return isFullyVaccinated;
    }

    public String getIsPregnant() {
        return isPregnant;
    }

    public String getMotherTagNo() {
        return motherTagNo;
    }

    public String getName() {
        return name;
    }

    public String getObtainedSource() {
        return obtainedSource;
    }

    public String getPregnantMonth() {
        return pregnantMonth;
    }

    public String getRemainingVaccine() {
        return remainingVaccine;
    }

    public String getRfirstVaccineDate() {
        return rfirstVaccineDate;
    }

    public String getRfirstVaccineName() {
        return rfirstVaccineName;
    }

    public String getRsecondVaccineName() {
        return rsecondVaccineName;
    }

    public String getRsecondVaccineDate() {
        return rsecondVaccineDate;
    }

    public String getRthirdVaccineDate() {
        return rthirdVaccineDate;
    }

    public String getRthirdVaccineName() {
        return rthirdVaccineName;
    }

    public String getSecondVaccineDate() {
        return secondVaccineDate;
    }

    public String getSecondVaccineName() {
        return secondVaccineName;
    }

    public String getTagNo() {
        return tagNo;
    }

    public String getThirdVaccineDate() {
        return thirdVaccineDate;
    }

    public String getThirdVaccineName() {
        return thirdVaccineName;
    }

    public String getVaccineComplete() {
        return vaccineComplete;
    }

    public String getVaccineDesc() {
        return vaccineDesc;
    }

    public String getWeight() {
        return weight;
    }

    public String getRefDoc() {
        return refDoc;
    }

    public Animal(String breed, String childNo, String dateOfBirth, String dateOfFarm, String desc, String fatherTagNo, String firstVaccineDate, String firstVaccineName, String fourthVaccineDate, String fourthVaccineName, String gender, String imageUri, String isFullyVaccinated, String isPregnant, String motherTagNo, String name, String obtainedSource, String pregnantMonth, String remainingVaccine, String rfirstVaccineDate, String rfirstVaccineName, String rsecondVaccineName, String rsecondVaccineDate, String rthirdVaccineDate, String rthirdVaccineName, String secondVaccineDate, String secondVaccineName, String tagNo, String thirdVaccineDate, String thirdVaccineName, String vaccineComplete, String vaccineDesc, String weight, String refDoc, String healthStatus) {
        this.breed = breed;
        this.childNo = childNo;
        this.dateOfBirth = dateOfBirth;
        this.dateOfFarm = dateOfFarm;
        this.desc = desc;
        this.fatherTagNo = fatherTagNo;
        this.firstVaccineDate = firstVaccineDate;
        this.firstVaccineName = firstVaccineName;
        this.fourthVaccineDate = fourthVaccineDate;
        this.fourthVaccineName = fourthVaccineName;
        this.gender = gender;
        this.imageUri = imageUri;
        this.isFullyVaccinated = isFullyVaccinated;
        this.isPregnant = isPregnant;
        this.motherTagNo = motherTagNo;
        this.name = name;
        this.obtainedSource = obtainedSource;
        this.pregnantMonth = pregnantMonth;
        this.remainingVaccine = remainingVaccine;
        this.rfirstVaccineDate = rfirstVaccineDate;
        this.rfirstVaccineName = rfirstVaccineName;
        this.rsecondVaccineName = rsecondVaccineName;
        this.rsecondVaccineDate = rsecondVaccineDate;
        this.rthirdVaccineDate = rthirdVaccineDate;
        this.rthirdVaccineName = rthirdVaccineName;
        this.secondVaccineDate = secondVaccineDate;
        this.secondVaccineName = secondVaccineName;
        this.tagNo = tagNo;
        this.thirdVaccineDate = thirdVaccineDate;
        this.thirdVaccineName = thirdVaccineName;
        this.vaccineComplete = vaccineComplete;
        this.vaccineDesc = vaccineDesc;
        this.weight = weight;
        this.refDoc = refDoc;
        this.healthStatus = healthStatus;
    }

    private String weight = "";
    private String refDoc = "";


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(breed);
        dest.writeString(childNo);
        dest.writeString(dateOfBirth);
        dest.writeString(dateOfFarm);
        dest.writeString(desc);
        dest.writeString(fatherTagNo);
        dest.writeString(firstVaccineDate);
        dest.writeString(firstVaccineName);
        dest.writeString(fourthVaccineDate);
        dest.writeString(fourthVaccineName);
        dest.writeString(gender);
        dest.writeString(imageUri);
        dest.writeString(isFullyVaccinated);
        dest.writeString(isPregnant);
        dest.writeString(motherTagNo);
        dest.writeString(name);
        dest.writeString(obtainedSource);
        dest.writeString(pregnantMonth);
        dest.writeString(remainingVaccine);
        dest.writeString(rfirstVaccineDate);
        dest.writeString(rfirstVaccineName);
        dest.writeString(rsecondVaccineName);
        dest.writeString(rsecondVaccineDate);
        dest.writeString(rthirdVaccineDate);
        dest.writeString(rthirdVaccineName);
        dest.writeString(secondVaccineDate);
        dest.writeString(secondVaccineName);
        dest.writeString(tagNo);
        dest.writeString(thirdVaccineDate);
        dest.writeString(thirdVaccineName);
        dest.writeString(vaccineComplete);
        dest.writeString(vaccineDesc);
        dest.writeString(weight);
        dest.writeString(refDoc);
        dest.writeString(healthStatus);
    }
}
