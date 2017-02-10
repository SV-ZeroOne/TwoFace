using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain.config
{
    class IssueEntityConfiguration : EntityTypeConfiguration<Issue>
    {
        public IssueEntityConfiguration()
        {
            this.Ignore(i => i.IssueID);
            this.HasMany(i => i.ComicCreator)
                .WithRequired(i => i.Issue);
        }
    }
}
