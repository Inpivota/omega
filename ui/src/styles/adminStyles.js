const drawerWidth = 240;

export const adminStyles = theme => ({
    root: {
        display: 'flex',
    },
    toolbar: theme.mixins.toolbar,
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
    },
    appBarButton: {
        color: theme.palette.primary.contrastText,
    },
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        width: drawerWidth,
    },
    link: {
        textDecoration: "none",
        color: "inherit"
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing.unit * 3,
    },
    pageWrapper: {
        marginLeft: drawerWidth,
        marginTop: theme.spacing.unit * 8,
        padding: theme.spacing.unit * 8,
        paddingTop: theme.spacing.unit * 4,
    },
    viewWrapper: {
        padding: theme.spacing.unit * 2,
    },
});