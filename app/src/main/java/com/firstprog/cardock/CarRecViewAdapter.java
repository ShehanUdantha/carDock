package com.firstprog.cardock;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CarRecViewAdapter extends RecyclerView.Adapter<CarRecViewAdapter.viewHolder>{

    private ArrayList<Car> cars = new ArrayList<>();
    private Context mContext;

    public CarRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
        //refresh a data in arraylist
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_car, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.carName.setText(cars.get(position).getCarModel());
        Glide.with(mContext)
                .asBitmap()
                .load(cars.get(position).getImgUrl())
                .into(holder.imgCar);
        holder.carPrice.setText(cars.get(position).getCarPrice());
        holder.carLocation.setText(cars.get(position).getOwnerAddress());
        holder.carBrand.setText(cars.get(position).getCarBrand());
        holder.carEngine.setText(cars.get(position).getCarEngine());
        holder.carFuelType.setText(cars.get(position).getFuelType());
        holder.carTransmission.setText(cars.get(position).getGear());
        holder.carMileage.setText(cars.get(position).getCarMileage());
        holder.carMOF.setText(cars.get(position).getManufactureYear());
        holder.carOptions.setText(cars.get(position).getDescription());
        holder.carDate.setText(cars.get(position).getUploadDate());
        holder.carOwner.setText(cars.get(position).getOwnerName());

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ cars.get(position).getOwnerPhone()));
                mContext.startActivity(callIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imgCar;
        private TextView carName, carPrice, carLocation, carBrand, carMOF, carEngine, carFuelType, carTransmission, carMileage, carOptions, carOwner, carDate, btnDelete;
        private Button btnCall;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imgCar = itemView.findViewById(R.id.imgCar);
            carName = itemView.findViewById(R.id.carName);
            carPrice = itemView.findViewById(R.id.carPrice);
            carLocation = itemView.findViewById(R.id.carLocation);
            carBrand = itemView.findViewById(R.id.carBrand);
            carMOF = itemView.findViewById(R.id.carMOF);
            carEngine = itemView.findViewById(R.id.carEngine);
            carFuelType = itemView.findViewById(R.id.carFuelType);
            carTransmission = itemView.findViewById(R.id.carTransmission);
            carMileage = itemView.findViewById(R.id.carMileage);
            carOptions = itemView.findViewById(R.id.carOptions);
            carOwner = itemView.findViewById(R.id.carOwner);
            carDate = itemView.findViewById(R.id.carDate);
            btnCall = itemView.findViewById(R.id.btnCall);
        }
    }
}
