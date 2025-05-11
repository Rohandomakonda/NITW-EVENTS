import React, { useState, useEffect } from "react";
import axios from "axios";
import Club from "../../components/Club_Card/Club.jsx";
import useGet from "../../customhooks/useGet.jsx";

function ViewClub() {
  const{data: clubs,loading,error}  = useGet("/viewclubs",null);
  console.log("details is " + clubs);

  return (
    <div className="public-events">
      <div className="top-image-container">
        <img
          src="https://images.pexels.com/photos/1763075/pexels-photo-1763075.jpeg"
          alt="Top Image"
          className="top-image"
        />
      </div>

      <div className="clubs-container">
        {clubs.map((club) => (
          <Club
            key={club.id}
            id={club.id}
            name={club.clubname}
            description={club.description}
            image={`data:image/jpeg;base64,${club.image}`} // Assuming club.image is base64
          />
        ))}
      </div>
    </div>
  );
}

export default ViewClub;
