package com.dwipal.practice.androidadvancepracticeapp.model;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.dwipal.practice.androidadvancepracticeapp.BR;
import com.dwipal.practice.androidadvancepracticeapp.databinding.ActivityQuardraticEquationSolverBinding;

public class Equation extends BaseObservable {

    String a, b,c;
    ActivityQuardraticEquationSolverBinding binding;

    public Equation(ActivityQuardraticEquationSolverBinding binding) {
        this.binding = binding;
    }
    public Equation(){}
    @Bindable
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
        notifyPropertyChanged(BR.a);
    }

    @Bindable
    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
        notifyPropertyChanged(BR.b);
    }

    @Bindable
    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
        notifyPropertyChanged(BR.c);
    }

    public void solveEquation(View v){
        int a = Integer.parseInt(getA());
        int b = Integer.parseInt(getB());
        int c = Integer.parseInt(getC());

        double delta = b * b - 4 * a * c;

        double x1, x2;
        if(delta>0){
             x1 = (-b + Math.sqrt(delta)) / (2 * a);
             x2 = (-b - Math.sqrt(delta)) / (2 * a);

             binding.txtSolution.setText("x1 : "+x1+" x2 : "+x2);
        } else if (delta<0) {
            binding.txtSolution.setText("No real roots");
        } else{
            x1 = x2 = -b / (2 * a);
            binding.txtSolution.setText("x1 : "+x1+" x2 : "+x2);
        }
    }
}
