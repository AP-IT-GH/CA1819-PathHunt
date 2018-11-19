﻿using PathHunt.DataLayer;
using PathHunt.DataLayer.Model;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

namespace PathHunt.BusinessLayer
{
    public class LocationsFacade
    {
        private GameContext context;

        public LocationsFacade(GameContext _context)
        {
            this.context = _context;
        }
        public List<Location> GetAllLocations()
        {
            return context.Locations.ToList();
        }
        public Location GetLocation(int id)
        {
            return context.Locations
                .Find(id);
        }
    }
}