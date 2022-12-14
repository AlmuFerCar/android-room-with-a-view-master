package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


public class ContactListAdapter extends ListAdapter<Contact, WordViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    public ContactListAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback) {
        super(diffCallback);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_component, viewGroup, false);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
                 view.setOnClickListener(this);
//        itemView.setOnLongClickListener(this);
//        AgendasViewHolder tvh =new AgendasViewHolder(itemView);
//        return tvh;
        return WordViewHolder.create(view);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Contact current = getItem(position);
        holder.bind(current.getmName(), current.getmPhone());
    }



    static class WordDiff extends DiffUtil.ItemCallback<Contact> {

        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getmName().equals(newItem.getmName());
        }
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }


    @Override
    public void onClick(View view) {
        if(listener !=null){
            listener.onClick(view);}
    }

    public Contact getContacto(int pos)
    {
       return getItem(pos);
    }

}
