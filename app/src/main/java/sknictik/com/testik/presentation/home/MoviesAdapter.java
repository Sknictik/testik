package sknictik.com.testik.presentation.home;

import com.squareup.picasso.Picasso;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sknictik.com.testik.R;
import sknictik.com.testik.databinding.ItemMovieBinding;
import sknictik.com.testik.domain.data.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movieList;

    private OnMovieClickListener onMovieClickListener;

    public MoviesAdapter(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setMovieList(List<Movie> movieList) {
        if (movieList != null) {
            this.movieList = new ArrayList<>(movieList);
            notifyDataSetChanged();
        }
    }

    public boolean isEmpty() {
        return movieList == null || movieList.isEmpty();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MovieViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movieList.get(position), onMovieClickListener);
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieBinding binding;

        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Movie movie, final OnMovieClickListener onMovieClickListener) {
            binding.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onMovieClickListener != null) {
                        onMovieClickListener.onMovieClick(movie);
                    }
                }
            });

            binding.setMovie(movie);
            //Actually could have done that via BindingAdapter
            Picasso.with(binding.getRoot().getContext())
                    .load(movie.getPreview())
                    .centerCrop()
                    .into(binding.preview);
        }
    }

    interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

}
